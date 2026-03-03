% funkcja oblicająca wektory wyrazów wolnych

function B = calculate_b(D, R)  
    B = zeros(165,3);
    for i=1:165
        B(i, :) = [D(i,1)^2 - D(i,4)^2 + R(4,1)^2 - R(1,1)^2 + R(4,2)^2 - R(1,2)^2 + R(4,3)^2 - R(1,3)^2
                   D(i,2)^2 - D(i,4)^2 + R(4,1)^2 - R(2,1)^2 + R(4,2)^2 - R(2,2)^2 + R(4,3)^2 - R(2,3)^2
                   D(i,3)^2 - D(i,4)^2 + R(4,1)^2 - R(3,1)^2 + R(4,2)^2 - R(3,2)^2 + R(4,3)^2 - R(3,3)^2];
    end    
end