% funkcja obiczająca współrzędne x i y osoby badanej w całym czasie badania

function [X, Y] = solve(D)
    
    [X, Y] = cellfun(@solve_one,D,'UniformOutput',false);

% funkcja obliczająca współrzędne x i y osoby badanej w jednej serii pomiarowej

function [x, y] = solve_one(d) % d - jedna komórka tablicy D
    
    d1 = d(:,1);
    d2 = d(:,2);

    x = (d1.^2 - d2.^2 + 16)/8;
    y = sqrt(abs(d1.^2 - x.^2));