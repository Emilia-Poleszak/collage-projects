% funkcja obliczjąca prędkość ruchu w całym czasie badania

function v = calculate_v(X, Y, T)

    V = cellfun(@calculate_v_one, X, Y, T, 'UniformOutput',false);
    Counter = cellfun(@numel, V, 'UniformOutput',false);
    V = cellfun(@sum, V, 'UniformOutput',false);
    c = sum(cell2mat(Counter));
    v = sum(cell2mat(V));
    v = v/c;

% funkcja obliczająca prędność osoby badanej w jednej serii pomiarowej

function v = calculate_v_one(x, y, t)
    
    n = size(t,1);
    vx = zeros(n-1,1);
    vy = zeros(n-1,1);
    
    for i=2:n
        vx(i-1) = (x(i) - x(i-1))/(t(i) - t(i-1));
        vy(i-1) = (y(i) - y(i-1))/(t(i) - t(i-1));
    end
    
    v = realsqrt(vy.^2 + vx.^2);