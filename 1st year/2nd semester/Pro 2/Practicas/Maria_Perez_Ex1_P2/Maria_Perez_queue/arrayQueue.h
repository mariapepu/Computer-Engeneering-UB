/* 
 * File:   arrayQueue.h
 * Author: maria
 *
 * Created on 14 / de març / 2019, 23:11
 */

#ifndef ARRAYQUEUE_H
#define	ARRAYQUEUE_H
#include <cstdlib>
#include <iostream>
#include <vector>

class arrayQueue {
// class queue definition
    public:
        arrayQueue (const int max_size);
        virtual ~arrayQueue();
        void enqueue (const int key);
        void dequeue();
        bool isFull();
        bool isEmpty();
        void print();
        const int getFront();
    
private:
    int _max_size;
    int _size;
    std::vector<int> _data;

};

#endif	/* ARRAYQUEUE_H */

