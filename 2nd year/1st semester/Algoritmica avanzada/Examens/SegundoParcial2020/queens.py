import numpy as np
import copy

class Queens:
    def __init__(self, n=8):
        self._board = np.zeros((n,n))
        self._free = np.zeros((n,n))
        self.n = n
    
    @property
    def empty(self):
        # Devuelve el conjunto de casillas que no tienen reinas
        return [(i, j) for i in range(self.n) for j in range(self.n) if self._board[i,j] == 0]
    
    @property
    def occupied(self):
        # Devuelve el conjunto de casillas que tienen reinas
        return [(i, j) for i in range(self.n) for j in range(self.n) if self._board[i,j] == 1]
    
    @property
    def free(self):
        # Devuelve el conjunto de casillas que NO están bajo ataque
        return [(i, j) for i in range(self.n) for j in range(self.n) if self._free[i,j] == 0]
    
    # Devuelve un NUEVO TABLERO con la reina situada en la posición pasada por parametro
    def add_queen(self, pos):
        new_b = copy.deepcopy(self)
        if not (pos in self.empty):
            raise Exception("Cannot add a queen in (%i, %i)" % (pos[0], pos[1]))
        
        new_b._board[pos] = 1
        # row 
        new_b._free[:, pos[1]] = 1
        # column
        new_b._free[pos[0], :] = 1
        # diagonal 1
        new_b._free[self.get_diagonal_idx(pos)] = 1
        # diagonal 2
        new_b._free[self.get_diagonal_rev_idx(pos)] = 1
        
        return new_b 
    
    # Comprueba si todas las casillas están bajo ataque por al menos una reina
    def is_all_under_attack(self):
        return len(self.free) == 0
    
    def get_diagonal_idx(self, pos):
        mat = self._board
        diag = []
        rev = False
        if pos[0] < pos[1]:
            x, y = pos[0], pos[1]
        else:
            x, y = pos[1], pos[0]
            rev = True
        d = abs(x-y)

        for i in range(mat.shape[0]-d):
            diag.append((i, i+d))

        if rev:
            diag = [(y, x) for x, y in diag]
        return ([x[0] for x in diag], [x[1] for x in diag])

    def get_diagonal_rev_idx(self, pos):
        mat = self._board
        diag = []
        rev = False
        x, y = mat.shape[0]-1-pos[0], pos[1]
        if x > y:
            x, y = y, x
            rev = True
        d = abs(x-y)
        for i in range(mat.shape[0]-d):
            diag.append((mat.shape[0]-1-i, i+d))
        if rev:
            diag = [(mat.shape[0]-1-y, mat.shape[0]-1-x) for x, y in diag]

        return ([x[0] for x in diag], [x[1] for x in diag])
    
    def __str__(self):
        str_ = "+"
        for i in self._board[0]:
            str_ += "---+"
        str_ += "\n"
        for i in self._board:
            str_ += "|"
            for j in i:
                str_ += "   |" if j == 0 else " Q |"
            str_ +="\n+"
            for j in i:
                str_ += "---+"
            str_ +="\n"
        return str_
