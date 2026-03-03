% funkcja do rozwiązania danego układu równań Lotki - Volterry przy pomocy
% dwukrokowej metody trapezów

function [x, y] = trapeze(p1, p2, p3, p4, t, h, x0, y0)

    n = length(t);
    x = zeros(n, 1);
    y = zeros(n, 1);
    
    x(1) = x0;
    y(1) = y0;
        
    fx = @(x, y) p1*x - p2*x*y;
    fy = @(x, y) p3*x*y - p4*y;

    Fx = @(xn1, yn1) xn1 - x(1) - h/2*(fx(x(1), y(1)) + fx(xn1, yn1));
    Fy = @(xn1, yn1) yn1 - y(1) - h/2*(fy(x(1), y(1)) + fy(xn1, yn1));
    opts = optimoptions('fsolve', 'Display', 'none');
    result = fsolve(@(z) [Fx(z(1), z(2)); Fy(z(1), z(2))], [x(1), y(1)], opts);
    x(2) = result(1);
    y(2) = result(2);

    for i = 3:n
        Fx = @(xn1, yn1) xn1 - x(i-2) - h*(fx(x(i-2), y(i-2)) + fx(xn1, yn1));
        Fy = @(xn1, yn1) yn1 - y(i-2) - h*(fy(x(i-2), y(i-2)) + fy(xn1, yn1));
        opts = optimoptions('fsolve', 'Display', 'none');
        result = fsolve(@(z) [Fx(z(1), z(2)); Fy(z(1), z(2))], [x(i-1), y(i-1)], opts);
        x(i) = result(1);
        y(i) = result(2);
    end