% funkcja obliczająca błędy do zadania 4

function [maxErrorX, detAD, condAD] = calculate_error_r(R, sigmaZ, A, D, number)

    RD = R;
    RD(number, 3) = R(number, 3).*(1 + sigmaZ);   
    AD = calculate_a(RD);

    XD = solve(AD, D, RD); 
    X = solve(A, D, R); 

    relErrorsX = zeros(165, 1);

    for i=1:165
        relErrorsX(i, 1) = norm(XD(i, :) - X(i, :))/norm(X(i, :));
    end

    maxErrorX = max(relErrorsX);  
    detAD = calculate_det(AD); 
    condAD = calculate_cond(AD);    
        
end