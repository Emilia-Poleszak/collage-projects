% funkcja do rozwiązania danego układu równań Lotki - Volterry przy pomocy
% otwartej metody Eulera

function [x, y] = open_euler(p1, p2, p3, p4, t, h, x0, y0)

    n = length(t);
    x = zeros(n, 1);
    y = zeros(n, 1);
    
    x(1) = x0;
    y(1) = y0;
    
    for i = 2:n    
        x(i) = x(i-1) + (p1*x(i-1) - p2*x(i-1)*y(i-1))*h;
        y(i) = y(i-1) + (p3*x(i-1)*y(i-1) - p4*y(i-1))*h;
    end