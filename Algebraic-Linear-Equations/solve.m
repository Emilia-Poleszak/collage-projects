% funkcja obliczająca rozwiązanie układu równań

function [X] = solve(A, D, R)   

    X = zeros(165,3);

    E21 = [1, 0, 0
           A(2,1)/A(1,1)*(-1), 1, 0
           0, 0, 1];
    U = E21*A; 
    
    E31 = [1, 0, 0
           0, 1, 0
           U(3,1)/U(1,1)*(-1), 0, 1];
    U = E31*U;
    
    E32 = [1, 0, 0
           0, 1, 0
           0, U(3,2)/U(2,2)*(-1), 1];
    U = E32*U;
    
    L = (E32*E31*E21)^(-1);     
    B = calculate_b(D,R);  
    
    for i=1:165
    
        y1 = B(i, 1);
        y2 = B(i, 2) - L(2,1)*y1;
        y3 = B(i, 3) - L(3,2)*y2 - L(3,1)*y1;
    
        x3 = y3 / U(3,3);
        x2 = (y2 - U(2,3)*x3) / U(2,2);
        x1 = (y1 - U(1,2)*x2 - U(1,3)*x3) / U(1,1);
    
        X(i,:) = [x1, x2, x3]; 
    
    end
end