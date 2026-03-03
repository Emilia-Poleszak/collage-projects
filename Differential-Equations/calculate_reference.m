% funkcja obliczająca wartości referencyjne 

function y = calculate_reference(p1, p2, p3, p4, x0, y0, t)

    opts = odeset('AbsTol', 1e-12, 'RelTol', 1e-8);
    [~, r] = ode45(@(t,y) derivative(p1, p2, p3, p4, y), t, [x0, y0], opts);

    y = r(:,2);