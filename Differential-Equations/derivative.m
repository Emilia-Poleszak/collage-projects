% funkcja obliczająca pochądną zmiennej y po czasie t na podstawie układu
% równań Lotki - Volterry

function dydt = derivative(p1, p2, p3, p4, y)
    
    dydt = [p1 * y(1) - p2 * y(1) * y(2);
            p3 * y(1) * y(2) - p4 * y(2)];