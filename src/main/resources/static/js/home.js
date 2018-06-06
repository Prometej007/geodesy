function changeTitleFile(i) {
    document.getElementById('label'+i).innerHTML = document.getElementById('file'+i).files[0].name;
}