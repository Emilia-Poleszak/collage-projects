% funkcja obliczająca macierz wyznaczników A

function A = calculate_a(R)
    
    A = [2*(R(4,1)-R(1,1)), 2*(R(4,2)-R(1,2)), 2*(R(4,3)-R(1,3))
         2*(R(4,1)-R(2,1)), 2*(R(4,2)-R(2,2)), 2*(R(4,3)-R(2,3))
         2*(R(4,1)-R(3,1)), 2*(R(4,2)-R(3,2)), 2*(R(4,3)-R(3,3))];
end