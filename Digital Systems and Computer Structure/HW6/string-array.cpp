    #include "string-array.h"
    
   

    
    StringArray::StringArray(){        
    }

    StringArray::~StringArray(){
        for (auto& item : my_string_array) {
            delete item;
        }
    }

    void StringArray::Push(GenericString* str){
            my_string_array.push_back(str);
    }

    GenericString* StringArray::operator[](int index) const {
        if (index >= 0 && index < size()) {
            return my_string_array.at(index);  // Use at() for bounds checking
        } else {
            return nullptr;
        }
        
    }
    int StringArray::size() const{
        return my_string_array.size();
    }
