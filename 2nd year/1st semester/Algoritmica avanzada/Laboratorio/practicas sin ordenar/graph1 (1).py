class Graph:

    def __init__(self):
        self._nodes = {}
        self._edges = {}
    
    @property
    def node(self):
        return self._nodes
    
    @property
    def edge(self):
        return self._edges
    
    def nodes(self):
        return [x for x in self._nodes.keys()]
    
    def edges(self):
        #return [(x,y) for x in self._nodes.keys() for y in self._nodes.keys[node]]
        edgesList=[]
        for node1,edge_list in self._edges.items():
            for node2 in edge_list.keys():
                
                posible_edge=(node1,node2) if node1<= node2 else(node2,node1)
                
                if posible_edge not in edgesList:
                    edgesList.append(posible_edge)
                    
        return edgesList
    
    def add_node(self, node, attr_dict=None):
        if attr_dict == None:
            attr_dict={}
        self._nodes[node] = attr_dict
        self._edges.setdefault(node,dict())
    
    def add_edge(self, node1, node2, attr_dict=None):
        if node1 not in self._nodes.keys():
            self.add_node(node1)
            
        if node2 not in self._nodes.keys():
            self.add_node(node2)
            
        attr=attr_dict if attr_dict else dict()
        
        old=self._edges[node1].get(node2,dict())
        old.update(attr)
        self._edges[node1][node2] = old
        self._edges[node2][node1] = old
                
            
    def add_nodes_from(self, node_list, attr_dict=None):
        for x in node_list:
            self.add_node(x,attr_dict)
            
    def add_edges_from(self, edge_list, attr_dict=None):
        for key,value in edge_list:
            self.add_edge(key,value,attr_dict)
    
    def degree(self,node):
        return [len(self._edges[node])]
        
        return len(self._edges.get(node,{}).keys())
    
    def __getitem__(self, node):
        return[(node,edge)for node in self._nodes.keys() for edge in self._edges.values()]
    
    def __len__(self):
        return len(self._nodes)
    
    def neighbors(self, node):
           return[node for node in self._edges.get(node,{}).keys()]

    
    def remove_node(self, node1):
        if node1 in self._nodes.keys():
            del self._nodes[node1]            
    
    def remove_edge(self, node1, node2):
        if node1 in self._edges[node2]:
            del self._edges[[node2][node1]]
        if node2 in self._edges[node1]:
            del self._edges[[node1][node2]]
    
    def remove_nodes_from(self, node_list):
        for x in node_list:
            self.remove_node(x)
    
    def remove_edges_from(self, edge_list):
        for i in edge_list:
            self.remove_edge(i[0],i[1])

