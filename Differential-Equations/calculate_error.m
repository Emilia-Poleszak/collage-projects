% funkcja obliczająca zagregowany błąd względny

function delta_y = calculate_error(y, yref)

    delta_y = (sqrt(sum((y - yref).^2)))/(sqrt(sum(yref.^2)));