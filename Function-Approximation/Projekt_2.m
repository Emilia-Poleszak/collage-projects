%% Projekt nr 2
% przedmiot: Metody Numeryczne (MNUB)
% autor: Emilia Poleszak

close all
clear

load ("MNUB_24L_P2_dane15.mat")

% zadanie 1

[X, Y] = solve(D);

figure(1);
hold on
for i=1:20
    plot(X{1,i}, Y{1,i})
end
scatter([0,4], [0,0], 'filled');
xlabel('x[m]');
ylabel('y[m]');
title('Wykres 1');
hold off

V = calculate_v(X, Y, t);
[xe, ye, ve] = estimate_error(X, Y, V, xref, yref, vref);

% zadanie 2

M = 18;
K = 9;
Dest = estimate_chebyshev(M, K, D, t);
[Xest, Yest] = solve(Dest);
Vest = calculate_v(Xest, Yest, t);
[xe2, ye2, ve2] = estimate_error(Xest, Yest, Vest, xref, yref, vref);

figure(2);
hold on
for i=1:20
    plot(Xest{1,i}, Yest{1,i});
end
scatter([0,4], [0,0], 'filled');
xlabel('x[m]');
ylabel('y[m]');
title('Wykres 2');
hold off

% zadanie 3

MKxe = zeros(16, 20);
MKye = zeros(16, 20);
MKve = zeros(16, 20);
ms = 5:20;
ks = 0:19;

for m=1:16
    M = m + 4;
    for K=0:M-1
        k = K + 1;
        Dest3 = estimate_chebyshev(M, K, D, t);
        [Xest3, Yest3] = solve(Dest3);    
        Vest3 = calculate_v(Xest3, Yest3, t);
        [MKxe(m,k), MKye(m,k), MKve(m,k)] = estimate_error(Xest3, Yest3, Vest3, xref, yref, vref);
    end
end

figure(3);
hold on
[Ks, Ms] = meshgrid(ks, ms);
surf(Ms, Ks, MKxe, log(MKxe));
xlabel('M');
ylabel('K');
zlabel('xe3 [m]')
title('Wykres 3.1');
hold off

figure(4);
hold on
[Ks, Ms] = meshgrid(ks, ms);
set(gca,'zscale','log') 
surf(Ms, Ks, MKxe, log(MKxe));
xlabel('M');
ylabel('K');
zlabel('xe3 [m]')
title('Wykres 3.2');
hold off

figure(5);
hold on
[Ks, Ms] = meshgrid(ks, ms);
surf(Ms, Ks, MKye, log(MKye));
xlabel('M');
ylabel('K');
zlabel('ye3 [m]')
title('Wykres 4.1');
hold off

figure(6);
hold on
[Ks, Ms] = meshgrid(ks, ms);
set(gca,'zscale','log') 
surf(Ms, Ks, MKye, log(MKye));
xlabel('M');
ylabel('K');
zlabel('ye3 [m]')
title('Wykres 4.2');
hold off

figure(7);
hold on
[Ks, Ms] = meshgrid(ks, ms);
surf(Ms, Ks, MKve, log(MKve));
xlabel('M');
ylabel('K');
zlabel('ve3 [m]')
title('Wykres 5.1');
hold off

figure(8);
hold on
[Ks, Ms] = meshgrid(ks, ms);
set(gca,'zscale','log') 
surf(Ms, Ks, MKve, log(MKve));
xlabel('M');
ylabel('K');
zlabel('ve3 [m]')
title('Wykres 5.2');
hold off

% zadanie 4

K = 9;
Dest4 = estimate_g(K, D, t);
[Xest4, Yest4] = solve(Dest4);

figure(9);
hold on
for i=1:20
    plot(Xest4{1,i}, Yest4{1,i});
end
scatter([0,4], [0,0], 'filled');
xlabel('x[m]');
ylabel('y[m]');
title('Wykres 6');
hold off

xe4 = zeros(1, 20);
ye4 = zeros(1, 20);
ve4 = zeros(1, 20);
ks = 0:19;

for K=0:19
    k = K + 1;
    Dest4 = estimate_g(K, D, t);
    [Xest4, Yest4] = solve(Dest4);    
    Vest4 = calculate_v(Xest4, Yest4, t);
    [xe4(k), ye4(k), ve4(k)] = estimate_error(Xest4, Yest4, Vest4, xref, yref, vref);
end

figure(10);
hold on
scatter(ks, xe4, "filled");
xlabel('K');
ylabel('xe4 [m]')
title('Wykres 7');
hold off

figure(11);
hold on
scatter(ks, ye4, 'filled');
xlabel('K');
ylabel('ye4 [m]')
title('Wykres 8');
hold off

figure(12);
hold on
scatter(ks, ve4, 'filled');
xlabel('K');
ylabel('ve4 [m]')
title('Wykres 9');
hold off

