% funkcja obliczająca błędy do zadania 3

function [DD, maxErrorX, estMaxErrorX] = calculate_error_d(D, sigmaD, A, R)

    C = randn(165, 4);  
    DD = D.*(1 + sigmaD.*C);    

    XD = solve(A, DD, R);  
    X = solve(A, D, R);  
    
    BD = calculate_b(DD, R);    
    B = calculate_b(D, R);  

    normsB = zeros(165, 1); % alokacja pamięci na normy wektorów b, błędy b, wektory x i błedy x
    absErrorsB = zeros(165, 1); % alokacja pamięci na bezwzględne błędy wektorów b
    normsX = zeros(165, 1); % alokacja pamięci na normy wektorów x
    absErrorsX = zeros(165, 1); % alokacja pamięci na bezwzględne błędy wektorów x

    for i=1:165
        normsX(i, 1) = norm(X(i, :));
        absErrorsX(i, 1) = norm(XD(i, :) - X(i, :));
        normsB(i, 1) = norm(B(i, :));
        absErrorsB(i, 1) = norm(BD(i, :) - B(i, :));
        
    end

    relErrorsX = absErrorsX./normsX;    
    maxErrorX = max(relErrorsX);    
    
    relErrorsB = absErrorsB./normsB;
    maxErrorB = max(relErrorsB); 
    
    estMaxErrorX = calculate_cond(A)*maxErrorB; 

end