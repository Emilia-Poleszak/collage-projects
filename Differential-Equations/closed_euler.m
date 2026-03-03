% funkcja do rozwiązania danego układu równań Lotki - Volterry przy pomocy
% zamkniętej metody Eulera

function [x, y] = closed_euler(p1, p2, p3, p4, t, h, x0, y0)

    n = length(t);
    x = zeros(n, 1); 
    x(1) = x0;
    y = zeros(n, 1);
    y(1) = y0;
    
    for i = 2:n
        Fx = @(xn1, yn1) xn1 - x(i-1) - h*(p1*xn1 - p2*xn1*yn1);
        Fy = @(xn1, yn1) yn1 - y(i-1) - h*(p3*xn1*yn1 - p4*yn1);
        opts = optimoptions('fsolve', 'Display', 'none');
        result = fsolve(@(z) [Fx(z(1), z(2)); Fy(z(1), z(2))], [x(i-1), y(i-1)], opts);
        x(i) = result(1);
        y(i) = result(2);
    end