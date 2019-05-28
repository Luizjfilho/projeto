

print('TEORIA DO GRAFO' )
print('------------------------------------------------')

class Petri:
    def __init__(self, dic=None):
        if dic is None:
            dic = []
        self.dic = dic

    def listarVertices(self):
        return list(self.dic)

    def listarArestas(self):
       return list(self.dic.values()) #listar AS chaves

    def addVertice(self, chave):
        if vertice not in self.dic:
            self.dic[vertice] = []

    def addAresta(self, chave, valorChave):
        array = self.dic.get(chave)
        array.append(valorChave)
        self.dic[chave] = array


    def removerAresta(self, chave, valorChave): #ADCIONE O VERTICE E A ARESTA A SER REMOVIDA
        array = self.dic.get(chave) #ARRAY ATRIBUIDO AO DICIONARIO AS ARESTAS PEGADO NO VERTICE INDICADO
        for i in array:
            if i == valorChave: # SE EXISTIR ARESTA INFORMADA
                array.remove(i) #REMOVER

    def removerVerticeAresta(self,chave):

        if chave in self.dic:
            self.dic.pop(chave)
        for i in self.dic:
            if i == chave: 
                chave.remove(i) #remove a chave

        for i in self.dic: # percorre cada valorChave igual e remove
            array = self.dic.get(i)
            for j in array:
                if j == chave:
                    array.remove(chave)




dicionario = {'Q': ['V', 'W'],
              'V': [],
              'R': ['Q', 'V', 'X'],
              'W': ['V', 'X'],
              'S': ['R', 'T'],
              'X': ['Y'],
              'T': ['X'],
              'Y': ['U', 'Z'],
              'Z': ['T', 'U']}

p = Petri(dicionario)
print(dicionario)

p.removerVerticeAresta('X')

#print(p.listarVertices())
print(dicionario)
#print(p.listarArestas())

