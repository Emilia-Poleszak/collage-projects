% funkcja do aproksymacji funkcji z użyciem wielomianów Czebyszewa

function Dest = estimate_g(K, D, t)
    
    Dest = cell(1,20);
    for i=1:20
        Dest{1,i} = estimate_g_both(K, D{1,i}, t{1,i});
    end

% funkcja do aproksymacji dwóch kolumn macierzy d

function dest = estimate_g_both(K, d, t)

    dest = d;
    dest(:,1) = estimate_g_one(K, d(:,1), t);
    dest(:,2) = estimate_g_one(K, d(:,2), t);

% funkcja do aproksymacji jednej kolumny jednej komórki macierzy d

function yest = estimate_g_one(K, y, x) % y - jedna kolumna z jednej komórki tablicy D

    xshort = linspace(x(1,1), x(end,1), K)'; % rówomiernie rozłożone K punktów z osi liczbowej 
    g = zeros(length(x),K);

    for i=1:K
        [~, idx] = min(abs(xshort(i)-x));
        xshort(i) = x(idx); % wybór równomiernie rozłożonych punktów z danych z zadania
    end

    for i=1:K
        g(:,i) = (1/(2*sqrt(2*pi)))*exp((-1/8)*(x - xshort(i)).^2);
    end
    
    pest=g\y;
    yest=g*pest;