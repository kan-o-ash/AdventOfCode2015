function grid = turn(grid, point1, point2, val)
% In grid of lights, turn a rectangle of lights on or off.
% Given two corners, point1 and point2
    for i = drange(point1(1),point2(1))
        for j = drange(point1(2),point2(2))
            grid(j+1,i+1) = max(0,grid(j+1,i+1) + val);
        end
    end
end

