%% Projekt nr 1
% przedmiot: Metody Numeryczne (MNUB)
% autor: Emilia Poleszak

close all
clear

load ("MNUB_24L_P1_dane15.mat")

% zadanie 2

A = calculate_a(R);
X = solve(A, D, R);

figure(1);  % wykres 1
plot3(X(:,1), X(:,2), X(:,3));
xlabel("x [m]");
ylabel("y [m]");
zlabel("z [m]");
hold on
scatter3(R(:,1), R(:,2), R(:,3));
text(R(:,1), R(:, 2), R(:, 3), {"R1", "R2", "R3", "R4"}, "VerticalAlignment", "bottom", "HorizontalAlignment", "left");
grid on
hold off

figure(2);  % wykres 2
plot(t,X(:,1),t,X(:,2),t,X(:,3))
legend("x", "y", "z");
xlabel("czas [s]");
ylabel("x, y, z [m]")
grid on

% zadanie 3

sigmaD = [0.001:0.001:0.009, 0.01:0.01:0.09, 0.1];
sizeSigmaD = size(sigmaD, 2);
DD = zeros(165, 4, sizeSigmaD);     
MaxError3 = zeros(sizeSigmaD, 1);   
EstMaxError3 = zeros(sizeSigmaD, 1);    

for i=1:sizeSigmaD
    [DD(:,:,i), MaxError3(i), EstMaxError3(i)] = calculate_error_d(D, sigmaD(i), A, R);
end

figure(3);  % wykres 3
loglog(sigmaD, MaxError3, sigmaD, EstMaxError3);
legend("MaxError3", "EstMaxError3");
grid on

% zadanie 4

sigmaZ = linspace(-0.1, 0.1, 150);
sizeSigmaZ = size(sigmaZ, 2);

MaxError4 = zeros(sizeSigmaZ, 4);   
condAD = zeros(sizeSigmaZ, 4);  
detAD = zeros(sizeSigmaZ, 4);   

for i=1:sizeSigmaZ
    for j=1:4
        [MaxError4(i, j), detAD(i, j), condAD(i, j)] = calculate_error_r(R, sigmaZ(i), A, D, j);
    end
end

figure(4);  % wykres 4
hold on
for i=1:4
    plot(sigmaZ, condAD(:, i));
end
legend("cond(AD_1)","cond(AD_2)", "cond(AD_3)", "cond(AD_4)");
grid on
hold off

figure(5)   % wykres 5
hold on
for i=1:4
    plot(sigmaZ, detAD(:, i));
end
legend("det(AD_1)","det(AD_2)","det(AD_3)","det(AD_4)");
grid on
hold off

figure(6)      % wykres 6
hold on
for i=1:4
    plot(sigmaZ, MaxError4(:,i));
end
legend("MaxError4_1","MaxError4_2","MaxError4_3","MaxError4_4");
grid on
hold off
