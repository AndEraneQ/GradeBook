#include "showbehavior.h"
#include "ui_showbehavior.h"
#include "student.h"
#include "comments.h"
#include <QMessageBox>
#include "fileData.h"
ShowBehavior::ShowBehavior(QWidget *parent)
    : QDialog(parent)
    , ui(new Ui::ShowBehavior)
{
    ui->setupUi(this);
    ui->gradesPlainTextEdit->setReadOnly(true);
    ui->posNegPlainTextEdit->setReadOnly(true);
    Person currentStudent;
    readCurrentUser(this,currentStudent,directoryName + "currentuser.bin");
    if(currentStudent.getRole()=="Nauczyciel"){
        readCurrentUser(this,currentStudent,directoryName + "currentstudent.bin");
    }
    ui->centralLabel->setText(QString::fromStdString("Zachowanie osoby: " + currentStudent.getName() +" " + currentStudent.getSurname()));
    Student student(currentStudent);
    string filename1=directoryName + student.getName()+"."+student.getSurname()+"behaviorGrade.bin";
    std::ifstream inputFile(filename1,std::ios::binary);
    try{
        GradeComment *gradeComm = new GradeComment;
        while(inputFile && !inputFile.eof()){
            gradeComm->readFromFile(inputFile);
            if(gradeComm->getGrade()!=0){
                student.comments.push_back(gradeComm);
            }
            gradeComm = new GradeComment;
        }
        delete gradeComm;
    }
    catch (std::exception& e) {
        QMessageBox::warning(this, "Error","Read data err");
    }
    inputFile.close();


    string filename2=directoryName + student.getName()+"."+student.getSurname()+ "behaviorpORn.bin";
    std::ifstream inputFile2(filename2,std::ios::binary);
    try{
        PositiveOrNegativeComment *posOrNegComm = new PositiveOrNegativeComment;
        while(posOrNegComm->readFromFile(inputFile2) && !inputFile2.eof()){
            student.comments.push_back(posOrNegComm);
            posOrNegComm = new PositiveOrNegativeComment;
        }
        delete posOrNegComm;
    }
    catch (std::exception& e) {
        QMessageBox::warning(this, "Error","Read data error");
    }
    inputFile2.close();
    GradeComment *gradeCommentPtr;
    PositiveOrNegativeComment *positiveOrNegativeCommentPtr;
    for(auto i=0u;i<student.comments.size();i++){
        if(student.comments[i]->getType()=="notGrade"){
            positiveOrNegativeCommentPtr = dynamic_cast<PositiveOrNegativeComment*>(student.comments[i]);
            ui->posNegPlainTextEdit->appendPlainText("Typ: ");
            ui->posNegPlainTextEdit->insertPlainText(QString::fromStdString(positiveOrNegativeCommentPtr->getPositiveOrNegative()));
            ui->posNegPlainTextEdit->insertPlainText(" Komentarz: ");
            ui->posNegPlainTextEdit->insertPlainText(QString::fromStdString(positiveOrNegativeCommentPtr->getComment()));
        }
        else if(student.comments[i]->getType()=="grade"){
            gradeCommentPtr = dynamic_cast<GradeComment*>(student.comments[i]);
            ui->gradesPlainTextEdit->appendPlainText("Ocena: ");
            ui->gradesPlainTextEdit->insertPlainText(QString::number(gradeCommentPtr->getGrade()));
            ui->gradesPlainTextEdit->insertPlainText(" Komentarz: ");
            ui->gradesPlainTextEdit->insertPlainText(QString::fromStdString(gradeCommentPtr->getComment()));
        }
    }
}

ShowBehavior::~ShowBehavior()
{
    delete ui;
}

void ShowBehavior::on_cancelPushButton_clicked()
{
    this->close();
}

