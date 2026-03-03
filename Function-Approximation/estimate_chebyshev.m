% funkcja do aproksymacji funkcji z użyciem wielomianów Czebyszewa

function Dest = estimate_chebyshev(M, K, D, t)
    
    Dest = cell(1,20);
    for i=1:20
        Dest{1,i} = estimate_chebyshev_both(M, K, D{1,i}, t{1,i});
    end

% funkcja do aproksymacji dwóch kolumn macierzy d

function dest = estimate_chebyshev_both(M, K, d, t)

    dest = d;
    dest(:,1) = estimate_chebyshev_one(M, K, d(:,1), t);
    dest(:,2) = estimate_chebyshev_one(M, K, d(:,2), t);

% funkcja do aproksymacji jednej kolumny jednej komórki macierzy d

function yest = estimate_chebyshev_one(M, K, y, x) % y - jedna kolumna z jednej komórki tablicy D
    
    N = length(x);
    m = (x(N) + x(1))/2;  
    xt = (x - m)/m;  % transformacja x na przedział [-1,1]

    xtshort = linspace(xt(1,1), xt(end,1), M)'; % rówomiernie rozłożone M punktów z osi liczbowej 
    yshort = zeros(M,1);
    
    for i=1:M
        [~, idx] = min(abs(xtshort(i)-xt));
        yshort(i) = y(idx); 
        xtshort(i) = xt(idx); % wybór równomiernie rozłożonych punktów z danych do zadania
    end
    
    f=[ones(N,1) xt];
    fshort=[ones(M,1) xtshort];

    for i=3:K+1
        f=[f (2*xt.*f(:,i-1)-f(:,i-2))];
        fshort=[fshort (2*xtshort.*fshort(:,i-1)-fshort(:,i-2))];
    end
    
    pest=fshort\yshort;
    yest=f*pest;