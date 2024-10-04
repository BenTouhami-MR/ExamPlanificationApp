function toggleSurveillantsField(salleId) {
    var checkbox = document.querySelector('input[value="' + salleId + '"]');
    var surveillantsField = document.getElementById('surveillants-' + salleId);
    if (checkbox.checked) {
        surveillantsField.style.display = 'block';
    } else {
        surveillantsField.style.display = 'none';
    }
}