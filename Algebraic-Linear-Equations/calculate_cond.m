% funkcja obliczająca liczbę kondycyjną macierzy

function condA = calculate_cond(A)
    condA = norm(A)*norm(A^(-1));
end