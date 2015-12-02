######################## PART 1 #####################
# Santa needs to know which floor of a building to go to
# input.txt contains directions
# '(' = one floor up
# ')' = one floor down

def OpenFile(file_name):
    f = open(file_name, 'r')
    return f.read()

def FindFloor(file_name):
    input_txt = OpenFile(file_name)
    floor_num = 0

    for char in input_txt:
        if char == "(":
            floor_num += 1
        elif char == ")":
            floor_num -= 1

    return floor_num
    
print FindFloor('input.txt')

######################## PART 2 #####################
# Find out how many moves it takes to get to the target floor

def NumMovesToFloor(file_name, floor_target):
    input_txt = OpenFile(file_name)
    floor_num = 0

    for i in range(len(input_txt)):
        ch = input_txt[i]
        if ch == "(":
            floor_num += 1
        elif ch == ")":
            floor_num -= 1
        if floor_num == floor_target:
            return i + 1

print NumMovesToFloor('input.txt', -1)
