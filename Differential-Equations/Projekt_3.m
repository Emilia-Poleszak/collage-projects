%% Projekt nr 3
% przedmiot: Metody Numeryczne (MNUB)
% autor: Emilia Poleszak

close all
clear

p1 = 15;    % dane z zadania
p2 = 0.12;
p3 = 0.04;
p4 = 12;
x0 = 530;
y0 = 30;
h = 0.005;
t = 0:h:1;

% zadanie 1

[t1, x1, y1] = lotka_volterra(p1, p2, p3, p4, x0, y0, t);
graph_lotka_volterra(t1, x1, y1, 'Wykres 1. Rozwiązanie układu równań Lotki - Volterry.');

% zadanie 2

[x_open, y_open] = open_euler(p1, p2, p3, p4, t, h, x0, y0);
[x_closed, y_closed] = closed_euler(p1, p2, p3, p4, t, h, x0, y0);
[x_heuns, y_heuns] = heuns(p1, p2, p3, p4, t, h, x0, y0);
[x_trapeze, y_trapeze] = trapeze(p1, p2, p3, p4, t, h, x0, y0);

graph_lotka_volterra(t, x_open, y_open, 'Wykres 2. Rozwiązanie układu równań Lotki - Volterry za pomocą otwartej metody Eulera.');
graph_lotka_volterra(t, x_closed, y_closed, 'Wykres 3. Rozwiązanie układu równań Lotki - Volterry za pomocą zamkniętej metody Eulera.');
graph_lotka_volterra(t, x_heuns, y_heuns, 'Wykres 4. Rozwiązanie układu równań Lotki - Volterry za pomocą metody Heuna.');
graph_lotka_volterra(t, x_trapeze, y_trapeze, 'Wykres 5. Rozwiązanie układu równań Lotki - Volterry za pomocą dwukrokowej metody trapezów.');

% zadanie 3

y3 = calculate_reference(p1, p2, p3, p4, x0, y0, t);
e_y_open = calculate_error(y_open, y3);
e_y_closed = calculate_error(y_closed, y3);
e_y_heuns = calculate_error(y_heuns, y3);
e_y_trapeze = calculate_error(y_trapeze, y3);

% zadanie 4

h4 = 0.0001:0.0001:0.01;
n = length(h4);
e_y_open_4 = zeros(n, 1);
e_y_closed_4 = zeros(n, 1);
e_y_heuns_4 = zeros(n, 1);
e_y_trapeze_4 = zeros(n, 1);

 for i=1:n
    t4 = 0:h4(i):1;
    y4 = calculate_reference(p1, p2, p3, p4, x0, y0, t4);
    [~, y_open_4] = open_euler(p1, p2, p3, p4, t4, h4(i), x0, y0);
    [~, y_closed_4] = closed_euler(p1, p2, p3, p4, t4, h4(i), x0, y0);
    [~, y_heuns_4] = heuns(p1, p2, p3, p4, t4, h4(i), x0, y0);
    [~, y_trapeze_4] = trapeze(p1, p2, p3, p4, t4, h4(i), x0, y0);

    e_y_open_4(i) = calculate_error(y_open_4, y4);
    e_y_closed_4(i) = calculate_error(y_closed_4, y4);
    e_y_heuns_4(i) = calculate_error(y_heuns_4, y4);
    e_y_trapeze_4(i) = calculate_error(y_trapeze_4, y4);
 end

figure;
loglog(h4, e_y_trapeze_4, 'DisplayName', 'dwukrokowa metoda trapezów');
hold on;
loglog(h4, e_y_heuns_4, 'DisplayName', 'metoda Heuna');
loglog(h4, e_y_closed_4, 'DisplayName', 'zamknięta metoda Eulera');
loglog(h4, e_y_open_4, 'DisplayName', 'otwarta metoda Eulera');
xlabel('h4');
ylabel('delta y');
legend;
title('Wykres 6. Zależności błędów od kroku całkowania w skali logarytmicznej.');
grid on;
hold off;

% zadanie 5

Table = readtable("MNUB_24L_P3_dane15.csv");
t5 = table2array(Table(:, 1));
x5 = table2array(Table(:, 2));
y5 = table2array(Table(:, 3));
p = [p1, p2, p3, p4];

function J = calculate_J(p, x5, y5, t5)
    [~, xd5, yd5] = lotka_volterra(p(1), p(2), p(3), p(4), x5(1), y5(1), t5);
    J = sum((xd5 - x5).^2) + sum((yd5 - y5).^2); 
end

p5 = fminsearch(@(p) calculate_J(p, x5, y5, t5), p);
