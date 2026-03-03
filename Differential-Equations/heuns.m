% funkcja do rozwiązania danego układu równań Lotki - Volterry przy pomocy
% metody Heuna

function [x, y] = heuns(p1, p2, p3, p4, t, h, x0, y0)

    n = length(t);
    x = zeros(n, 1);
    y = zeros(n, 1);
    
    x(1) = x0;
    y(1) = y0;
    
    for i = 2:n
        fx1 = p1*x(i-1) - p2*x(i-1)*y(i-1);
        fy1 = p3*x(i-1)*y(i-1) - p4*y(i-1);
        xp = x(i-1) + h*fx1;
        yp = y(i-1) + h*fy1;
        fx2 = p1*xp - p2*xp*yp;
        fy2 = p3*xp*yp - p4*yp;
        x(i) = x(i-1) + h/2*(fx1 + fx2);
        y(i) = y(i-1) + h/2*(fy1 + fy2);
    end