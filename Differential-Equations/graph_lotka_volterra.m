% funkcja tworząca wykresy populacji ofiar i drapieżników

function graph_lotka_volterra(t, x, y, graph_title)

    figure;
    hold on
    plot(t, x, 'b', 'DisplayName', 'Ofiary');
    plot(t, y, 'r', 'DisplayName', 'Drapieżniki');
    xlabel('Czas');
    ylabel('Populacja');
    legend;
    title(graph_title);
    grid on;
    hold off;