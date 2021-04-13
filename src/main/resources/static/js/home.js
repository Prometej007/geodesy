function changeTitleFile(i) {
    document.getElementById('label' + i).innerHTML = document.getElementById('file' + i).files[0].name;
}

// ----------- FIRST METHOD -------------

const firstMethodData = {
    name: null,
    calculationTypeName: null,
    approximationMoveList: [],
    reperList: []
};

function addNewReper() {
    event.preventDefault();
    const form = $('#first-metod-reper-form');
    const [name, height] = form.serializeArray();
    if (form[0].checkValidity()) {
        const reper = {
            name: name.value,
            height: parseFloat(height.value)
        }
        firstMethodData.reperList.push(reper);
        form.trigger("reset");
        renderReperList();
    }
}

function deleteReper() {
    const target = event.target;
    const index = parseFloat(target.dataset.index);
    firstMethodData.reperList.splice(index, 1);
    renderReperList();
    renderReperOptions();
}

function renderReperList() {
    const reperList = $('#first-method-repers');
    reperList.html('');
    if (firstMethodData.reperList.length) {
        const table = document.createElement('table');
        table.classList.add('main-table');
        table.innerHTML = `<tr><th>№</th> <th>Назва</th> <th>Висота</th> <th style="width: 20px"></th></tr>`;
        firstMethodData.reperList.forEach((reper, index) => {
            const tr = `<tr>
                        <td>${index + 1}</td>
                        <td>${reper.name}</td>
                        <td>${reper.height}</td>
                        <th style="width: 20px"><button data-index="${index}" style="width: 20px" type="button" onclick="deleteReper()">-</button></th>
                    </tr>`;
            table.innerHTML += tr;
        });
        reperList.append(table);
    }
}

function addNewStep() {
    event.preventDefault();
    const form = $('#first-metod-step-form');
    const [name, difference, stationCount, distance] = form.serializeArray();
    if (form[0].checkValidity()) {
        const step = {
            name: name.value,
            difference: parseFloat(difference.value),
            stationCount: parseFloat(stationCount.value),
            distance: parseFloat(distance.value),
        }
        firstMethodData.approximationMoveList.push(step);
        renderStepList();
        form.trigger("reset");
    }
}

function deleteStep() {
    const target = event.target;
    const index = parseFloat(target.dataset.index);
    firstMethodData.approximationMoveList.splice(index, 1);
    renderStepList();
}

function renderStepList() {
    const stepList = $('#first-method-steps');
    stepList.html('');
    if (firstMethodData.approximationMoveList.length) {
        const table = document.createElement('table');
        table.classList.add('main-table');
        table.innerHTML = `<tr><th>№</th> <th>Назва</th> <th>Різниця</th> <th>Кількість станцій</th> <th>Дистанція</th> <th></th></tr>`;
        firstMethodData.approximationMoveList.forEach((step, index) => {
            const tr = `<tr>
                        <td>${index + 1}</td>
                        <td>${step.name}</td>
                        <td>${step.difference}</td>
                        <td>${step.stationCount}</td>
                        <td>${step.distance}</td>
                        <th style="width: 20px"><button data-index="${index}" style="width: 20px" type="button" onclick="deleteStep()">-</button></th>
                    </tr>`;
            table.innerHTML += tr;
        });
        stepList.append(table);
    }
}

function submitFirstMethod() {
    const form = $('#first-metod-main-form');
    const [name, type] = form.serializeArray();
    firstMethodData.name = name.value;
    firstMethodData.calculationTypeName = type.value;
    if (firstMethodData.name &&
        firstMethodData.calculationTypeName &&
        firstMethodData.reperList.length &&
        firstMethodData.approximationMoveList.length) {
        $.ajax({
            type: "POST",
            contentType: 'application/json',
            url: '/calculation/v2/result-1',
            data: JSON.stringify(firstMethodData),
            success: function (data) {
                $('html').html(data);
            }
        });
    }
}

// ----------- FIRST METHOD -------------



