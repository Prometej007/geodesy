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
    event.preventDefault();
    event.stopPropagation();
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


// ----------- SECOND METHOD -------------

const secondMethodData = {
    name: null,
    calculationTypeName: null,
    reperList: [],
    poligonList: []
}

function addNewReperPoligon() {
    event.preventDefault();
    const form = $('#second-metod-reper-form');
    const [name, height] = form.serializeArray();
    if (form[0].checkValidity()) {
        const reper = {
            name: name.value,
            height: parseFloat(height.value)
        }
        secondMethodData.reperList.push(reper);
        form.trigger("reset");
        renderReperListPoligon();
    }
}

function deleteReperPoligon() {
    const target = event.target;
    const index = parseFloat(target.dataset.index);
    secondMethodData.reperList.splice(index, 1);
    renderReperListPoligon();
}

function renderReperListPoligon() {
    const reperList = $('#second-method-repers');
    reperList.html('');
    if (secondMethodData.reperList.length) {
        const table = document.createElement('table');
        table.classList.add('main-table');
        table.innerHTML = `<tr><th>№</th> <th>Назва</th> <th>Висота</th> <th style="width: 20px"></th></tr>`;
        secondMethodData.reperList.forEach((reper, index) => {
            const tr = `<tr>
                        <td>${index + 1}</td>
                        <td>${reper.name}</td>
                        <td>${reper.height}</td>
                        <th style="width: 20px"><button data-index="${index}" style="width: 20px" type="button" onclick="deleteReperPoligon()">-</button></th>
                    </tr>`;
            table.innerHTML += tr;
        });
        reperList.append(table);
    }
}

function addNewPoligonMove() {
    event.preventDefault();
    const form = $('#second-metod-poligon-form');
    const [name, moveName, difference, stationCount, distance] = form.serializeArray();
    if (form[0].checkValidity()) {
        const move = {
            name: moveName.value,
            difference: parseFloat(difference.value),
            stationCount: parseFloat(stationCount.value),
            distance: parseFloat(distance.value),
        }
        const poligon = secondMethodData.poligonList.find(p => p.name == name.value);
        if (poligon) {
            poligon.poligonMoves.push(move);
        } else {
            secondMethodData.poligonList.push({
                name: name.value,
                poligonMoves: [move]
            });
        }
        renderPoligonMoveList(name.value);
        form.trigger("reset");
        form.find('input[name=name]').val(name.value);
        console.error(secondMethodData)
    }
}

function deletePoligonMove() {
    const target = event.target;
    const index = parseFloat(target.dataset.index);
    const name = parseFloat(target.dataset.poligon);
    const poligon = secondMethodData.poligonList.find(p => p.name == name);
    if (poligon) {
        poligon.poligonMoves.splice(index, 1);
        if (!poligon.poligonMoves.length) {
            secondMethodData.poligonList = secondMethodData.poligonList.filter(pL => pL !== poligon);
        }
        renderPoligonMoveList();
    }
}

function deletePoligon() {
    const target = event.target;
    const index = parseFloat(target.dataset.index);
    secondMethodData.poligonList.splice(index, 1);
    renderPoligonMoveList();
}

function renderPoligonMoveList() {
    const stepList = $('#second-method-poligon-moves');
    stepList.html('');
    if (secondMethodData.poligonList.length) {
        secondMethodData.poligonList.forEach((poligon, pIndex) => {
            const table = document.createElement('table');
            table.classList.add('main-table');
            table.classList.add('main-mb-10');
            table.innerHTML = `<tr>
                                <td colspan="5">${poligon.name}</td>
                                <th style="width: 20px"><button data-index="${pIndex}" style="width: 20px" type="button" onclick="deletePoligon()">-</button></th>
                                </tr>`;
            table.innerHTML += `<tr><th>№</th> <th>Назва</th> <th>Різниця</th> <th>Кількість станцій</th> <th>Дистанція</th> <th></th></tr>`;
            poligon.poligonMoves.forEach((step, index) => {
                const tr = `<tr>
                            <td>${index + 1}</td>
                            <td>${step.name}</td>
                            <td>${step.difference}</td>
                            <td>${step.stationCount}</td>
                            <td>${step.distance}</td>
                            <th style="width: 20px"><button data-index="${index}" data-poligon="${poligon.name}" style="width: 20px" type="button" onclick="deletePoligonMove()">-</button></th>
                            </tr>`;
                table.innerHTML += tr;
            });
            stepList.append(table);
        });
    }
}

function submitSecondMethod() {
    event.preventDefault();
    event.stopPropagation();
    const form = $('#second-metod-main-form');
    const [name, type] = form.serializeArray();
    secondMethodData.name = name.value;
    secondMethodData.calculationTypeName = type.value;
    if (secondMethodData.name &&
        secondMethodData.calculationTypeName &&
        secondMethodData.reperList.length &&
        secondMethodData.poligonList.length) {
        $.ajax({
            type: "POST",
            contentType: 'application/json',
            url: '/calculation/v2/result-2',
            data: JSON.stringify(secondMethodData),
            success: function (data) {
                $('html').html(data);
            }
        });
    }
}

// ----------- SECOND METHOD -------------
