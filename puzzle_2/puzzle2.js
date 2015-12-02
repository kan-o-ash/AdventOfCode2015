// Given length l, width w, height h  of boxes
// Amount wrapping paper needed for each box:
// surface_area = l*w*2 + l*h*2 + w*h*2 + smallest_side
// find total surface area needed for orders in input.txt

var fs = require('fs');

function OpenFile (filename) {
    var input = fs.readFileSync(filename, 'utf8');
    return input.split('\n');
}

function CalcSurfaceArea(l,w,h) {
    var lw = l*w, lh = l*h, wh = w*h;
    return lw*2 + lh*2 + wh*2 + Math.min(lw,lh,wh);
}

function GetTotalArea(filename) {
    var input_arr = OpenFile(filename);
    var SA = 0;

    for (var i=0; i<input_arr.length; i++) {
        if (input_arr[i]) {
            var line_arr = input_arr[i].split('x');
            SA += CalcSurfaceArea(parseInt(line_arr[0]), parseInt(line_arr[1]), parseInt(line_arr[2]));
        }
    }

    return SA;
}

console.log(GetTotalArea('input.txt'));