# Santa needs to know which floor of a building to go to
# input.txt contains directions
# '(' = one floor up
# ')' = one floor down

def OpenFile(file_name):
    f = open(file_name, 'r')
    return f.read()

def FindFloor(file_name):
    string_input = OpenFile(file_name)
    count = 0
    for char in string_input:
        if char == "(":
            count += 1
        elif char == ")":
            count -= 1

    return count
    
print FindFloor('input.txt')