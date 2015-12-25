use std::io::BufReader;
use std::io::prelude::*;
use std::fs::File;
use std::collections::HashMap;

fn file_to_map(filename: &str, map: &mut HashMap<String, Vec<String>>) {
    let f = match File::open(filename) {
        Ok(v) => v,
        Err(e) => {
            println!("Could not Open file! {}", e);
            return;
        }
    };
    let f = BufReader::new(f);

    let mut vec: Vec<String>;
    let mut left_side: Vec<String>;

    for line in f.lines() {
        vec =  line.unwrap().split(" -> ").map(String::from).collect();
        left_side = vec[0].split(" ").map(String::from).collect();
        map.insert(vec[1].to_owned(), left_side);
    }
}

fn resolve_dep(instructions: & HashMap<String, Vec<String>>, resolved_wires: &mut HashMap<String, u16>, dep: &String) -> u16{
    match dep.parse::<u16>() {
        Ok(n) => { // it's a value
            resolved_wires.insert(dep.to_owned(), n);
            return n;
        }, Err(e) => { // it's a wire
            resolve_wire(instructions, resolved_wires, &dep);
            let val = resolved_wires.get(&dep.to_owned()).unwrap().to_owned();
            return val;
        },
    }
}

fn resolve_wire(instructions: & HashMap<String, Vec<String>>, resolved_wires: &mut HashMap<String, u16>, wire: &String){
    if resolved_wires.contains_key(wire) { return; }
    let instr = instructions.get(wire).unwrap();

    match instr.len() {
        1 => {
            let dep = instr[0].to_owned();
            let val = resolve_dep(instructions, resolved_wires, &dep);
            resolved_wires.insert(wire.to_owned(), val);
        },
        2 => { // must be a NOT
            let dep = instr[1].to_owned();
            let dep_val = resolve_dep(instructions, resolved_wires, &dep);
            let val = !dep_val;
            resolved_wires.insert(wire.to_owned(), val);
        },
        3 => {
            let op = instr[1].to_owned();
            let ldep = instr[0].to_owned();
            let rdep = instr[2].to_owned();

            let lval = resolve_dep(instructions, resolved_wires, &ldep);
            let rval = resolve_dep(instructions, resolved_wires, &rdep);
            let mut val: u16 = 0;
            match op.as_ref() {
                "OR" => val = lval | rval,
                "LSHIFT" => val = lval << rval,
                "RSHIFT" => val = lval >> rval,
                "AND" => val = lval & rval,
                _ => print!("shit!"),
            }
            resolved_wires.insert(wire.to_owned(), val);
        },
        _ => print!("shit!"),
    }
}

fn main() {
    let mut instructions = HashMap::new();
    let mut resolved_wires = HashMap::new();

    file_to_map("input.txt", &mut instructions);

    for wire in instructions.keys() {
        resolve_wire(&instructions, &mut resolved_wires, wire);
    }
    println!("Y IS!!!! {}", resolved_wires.get("a").unwrap())
}

