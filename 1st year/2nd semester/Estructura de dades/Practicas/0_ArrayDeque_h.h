/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   ArrayDeque.h
 * Author: Maria
 *
 * Created on 28 / de març / 2021, 01:46
 */

#ifndef ARRAYDEQUE_H
#define ARRAYDEQUE_H

class ArrayDeque {
public:
    ArrayDeque(const int a);
    ArrayDeque(const ArrayDeque& orig);
    virtual ~ArrayDeque();
    
    void enqueueBack(const int key);
    void dequeueBack();
    void enqueueFront(const int key);
    void dequeueFront();
    bool isFull();
    bool isEmpty();
    void print();
    const int getFront();
    const int getBack();
private:
    int _max_size;
    int _size;
    std::vector<int> _data;
};

#endif /* ARRAYDEQUE_H */

