function grid = toggle(grid, point1, point2)
% In grid of lights, toggle a rectangle of lights.
% Given two corners, point1 and point2
    for i = drange(point1(1)+1,point2(1)+1)
        for j = drange(point1(2)+1,point2(2)+1)
            grid(j,i) = 1 - grid(j,i);
        end
    end
end

