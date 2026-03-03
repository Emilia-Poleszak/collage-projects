% funkcja szczująca błąd bezwględny współrzędnych x, y i prędkości oraz ich średnie wartości

function [xe, ye, ve] = estimate_error(X, Y, v, xref, yref, vref)

   Xe = cellfun(@estimate_error_one, X, xref, 'UniformOutput',false);
   Ye = cellfun(@estimate_error_one, Y, yref, 'UniformOutput',false);
   ve = abs(v - vref);
   xe = calc_mean(Xe);
   ye = calc_mean(Ye);

% funkcja szacująca błąd bezwzględny dla jednej danej 
   
function e = estimate_error_one(m, ref) % m - wynik obliczeń, ref - wartość referencyjna

    e = abs(m - ref);

% funkcja obliczająca wartość średnią ze wszytskich elementów tabliczy komórkowej

function r = calc_mean(R) % R - tablica komórkowa macierzy liczb

    Counter = cellfun(@numel, R, 'UniformOutput',false); % counter - licznik liczby elementów w tablicach R
    R = cellfun(@sum, R, 'UniformOutput',false);
    c = sum(cell2mat(Counter));
    r = sum(cell2mat(R));
    r = r/c;