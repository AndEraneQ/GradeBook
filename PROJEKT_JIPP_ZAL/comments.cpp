#include <iostream>
#include "comments.h"

Comments::Comments() {
    this->comment="brak";
    this->commentSize=comment.size();
    this->type="brak";
    this->typeSize=type.size();
}

Comments::Comments(std::string comment,std::string type){
    this->comment=comment;
    this->commentSize=comment.size();
    this->type=type;
    this->typeSize=type.size();
}
Comments::~Comments(){
    //
}
PositiveOrNegativeComment::~PositiveOrNegativeComment(){
    //
}
GradeComment::~GradeComment(){
    //
}

std::string PositiveOrNegativeComment::getPositiveOrNegative(){
    return positiveOrNegative;
}

std::string Comments::getComment(){
    return comment;
}

std::string Comments::getType(){
    return type;
}

int GradeComment::getGrade(){
    return grade;
}

GradeComment::GradeComment(){
    this->grade=0;
    this->comment="brak";
    this->commentSize=comment.size();
    this->type="grade";
    this->typeSize=type.size();
}

GradeComment::GradeComment(Comments c1){
    this->comment=c1.getComment();
    this->commentSize=comment.size();
    //this->grade=c1.getGrade();
    this->type="grade";
    this->typeSize=type.size();
}

GradeComment::GradeComment(int grade, std::string comment){
    this->grade=grade;
    this->comment=comment;
    this->commentSize=comment.size();
    this->type="grade";
    this->typeSize=type.size();
}

std::string GradeComment::getType(){
    return type;
}

std::string GradeComment::getComment(){
    return comment;
}

PositiveOrNegativeComment::PositiveOrNegativeComment(){
    this->positiveOrNegative="brak";
    this->comment="brak";
    this->positiveOrNegativeSize=positiveOrNegative.size();
    this->commentSize=comment.size();
    this->type="notGrade";
    this->typeSize=type.size();
}

PositiveOrNegativeComment::PositiveOrNegativeComment(Comments c1){
    this->comment=c1.getComment();
    this->commentSize=comment.size();
    //this->positiveOrNegative=c1.getPositiveOrNegative();
    this->positiveOrNegativeSize=positiveOrNegative.size();
    this->type="notGrade";
    this->typeSize=type.size();
}

PositiveOrNegativeComment::PositiveOrNegativeComment(std::string comment,std::string positiveOrNegative){
    this->comment=comment;
    this->positiveOrNegative=positiveOrNegative;
    this->positiveOrNegativeSize=positiveOrNegative.size()+1;
    this->commentSize=comment.size();
    this->type="notGrade";
    this->typeSize=type.size();
}

std::string PositiveOrNegativeComment::getType(){
    return type;
}

std::string PositiveOrNegativeComment::getComment(){
    return comment;
}

void PositiveOrNegativeComment::writeToFile(std::ofstream &file){
    try{
        if(file.good() && file.is_open()){
            commentSize = comment.size();
            file.write((char*)&commentSize,sizeof(commentSize));
            file.write((char*)&comment[0],commentSize);

            positiveOrNegativeSize = positiveOrNegative.size();
            file.write((char*)&positiveOrNegativeSize,sizeof(positiveOrNegativeSize));
            file.write((char*)&positiveOrNegative[0],positiveOrNegativeSize);

            typeSize=type.size();
            file.write((char*)&typeSize,sizeof(typeSize));
            file.write((char*)&type[0],typeSize);
        }
        else{
           throw std::runtime_error("Couldn't open a file");
        }
    }
    catch(std::exception &e)
    {
        std::cerr << e.what() << std::endl;
    }
}

void GradeComment::writeToFile(std::ofstream &file){
    try{
        if(file.good() && file.is_open()){
            commentSize = comment.size();
            file.write((char*)&commentSize,sizeof(commentSize));
            file.write((char*)&comment[0],commentSize);

            typeSize=type.size();
            file.write((char*)&typeSize,sizeof(typeSize));
            file.write((char*)&type[0],typeSize);

            file << " ";
            file << grade;

        }
        else{
            throw std::runtime_error("Couldn't open a file");
        }
    }
    catch(std::exception &e)
    {
        std::cerr << e.what() << std::endl;
    }
}

bool PositiveOrNegativeComment::readFromFile(std::ifstream &file){
    try
    {
        if (file.good() && file.is_open())
        {
            file.read((char *)&commentSize, sizeof(commentSize));
            comment.resize(commentSize);
            file.read((char *)&comment[0], commentSize);

            file.read((char *)&positiveOrNegativeSize, sizeof(positiveOrNegativeSize));
            positiveOrNegative.resize(positiveOrNegativeSize);
            file.read((char *)&positiveOrNegative[0], positiveOrNegativeSize);

            file.read((char *)&typeSize, sizeof(typeSize));
            type.resize(typeSize);
            file.read((char *)&type[0], typeSize);
            return true;
        }
    }
    catch(std::exception &e)
    {
        std::cerr << e.what() << std::endl;
    }
    return false;
}

bool GradeComment::readFromFile(std::ifstream &file){
    try
    {
        if (file.good() && file.is_open())
        {
            file.read((char *)&commentSize, sizeof(commentSize));
            comment.resize(commentSize);
            file.read((char *)&comment[0], commentSize);

            file.read((char *)&typeSize, sizeof(typeSize));
            type.resize(typeSize);
            file.read((char *)&type[0], typeSize);

            file>>grade;
            return true;
        }
    }
    catch(std::exception &e)
    {
        std::cerr << e.what() << std::endl;
    }
    return false;
}


