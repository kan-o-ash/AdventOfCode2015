/* PART 1 */
// Given length l, width w, height h  of boxes
// Amount wrapping paper needed for each box:
// surface_area = l*w*2 + l*h*2 + w*h*2 + smallest_side
// find total surface area needed for orders in input.txt

var fs = require('fs');

function OpenFile(filename) {
    var input = fs.readFileSync(filename, 'utf8');
    return input.split('\n');
}

function SurfaceArea(l,w,h) {
    return l*w*2 + l*h*2 + w*h*2;
}

function WrapNeededForBox(l,w,h) {
    return SurfaceArea(l,w,h) + Math.min(l*w,l*h,w*h);
}

function GetTotalArea(filename) {
    var input_arr = OpenFile(filename);
    var SA = 0;

    for (var i=0; i<input_arr.length; i++) {
        if (input_arr[i]) {
            var line_arr = input_arr[i].split('x');
            SA += WrapNeededForBox(parseInt(line_arr[0]),
                                  parseInt(line_arr[1]),
                                  parseInt(line_arr[2]));
        }
    }

    return SA;
}

console.log(GetTotalArea('input.txt')); // Answer: 1606483

/* PART 2 */
// Get amount of ribbon needed.
// amount += smallest perimeter of a face
// amount += volume

function SmallestPeri(l,w,h) {
    return (l+w+h-Math.max(l,w,h))*2
}

function GetTotalRibbon(filename) {
    var input_arr = OpenFile(filename);
    var total_rib = 0;
    var l,w,h;

    for (var i=0; i<input_arr.length; i++) {
        if (input_arr[i]) {
            line_arr = input_arr[i].split('x');
            l = parseInt(line_arr[0]);
            w = parseInt(line_arr[1]);
            h = parseInt(line_arr[2]);
            total_rib += SmallestPeri(l,w,h) + l*w*h; // volume
            console.log(total_rib)
        }
    }

    return total_rib;
}

console.log(GetTotalRibbon('input.txt'));
// console.log(SmallestPeri(2,3, 10));