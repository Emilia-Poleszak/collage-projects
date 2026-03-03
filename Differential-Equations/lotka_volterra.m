% funkcja do wyznaczania wyników układu równań Lotki - Volttery

function [t, x, y] = lotka_volterra(p1, p2, p3, p4, x0, y0, tspan)

    [t, r] = ode45(@(t,y) derivative(p1, p2, p3, p4, y), tspan, [x0, y0]);

    x = r(:,1);
    y = r(:,2);