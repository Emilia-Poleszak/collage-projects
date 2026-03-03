% funkcja obliczająca wyznacznik macierzy

function detA = calculate_det(A)

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

        % ogólnie det(A) = det(L)*det(U), ale L = E32*E31*E21, 
        % więc det (L) = 1, więc det (A) = 1*det(U) = det(U)

    detA = 1;   
    for i=1:3
        detA = detA*U(i,i);
    end
end