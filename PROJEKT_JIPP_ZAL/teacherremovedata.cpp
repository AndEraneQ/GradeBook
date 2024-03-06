#include "teacherremovedata.h"
#include "ui_teacherremovedata.h"
#include "person.h"
#include "teacher.h"
#include "student.h"
#include <QFile>
#include <iostream>
#include "fileData.h"
Student studentDataDeleter;

void TeacherRemoveData::removeGradeUppdateData(){
    studentDataDeleter.grades.clear();
    ui->deleteComboBox->clear();
    QString filename=QString::fromStdString(directoryName) + ui->personComboBox->currentText().replace(' ','.') + ui->subjectComboBox->currentText() + ".bin";
    std::ifstream inputFile(filename.toStdString(),std::ios::binary);
    try{
        int i=1;
        int singleGrade;
        while(readGrades(inputFile,singleGrade) && !inputFile.eof()){
            studentDataDeleter.grades.push_back(singleGrade);
            ui->deleteComboBox->addItem(QString::number(i) + ". Ocena: " + QString::number(singleGrade));
            i++;
        }
    }
    catch (std::exception& e) {
        std::cerr << "Read data error: " << e.what() << std::endl;
    }
    inputFile.close();
}

void TeacherRemoveData::removeBehaviorUppdateData(){
    ui->deleteComboBox->clear();
    studentDataDeleter.comments.clear();
    string filename1=directoryName + ui->personComboBox->currentText().replace(' ','.').toStdString()+"behaviorGrade.bin";
    std::ifstream inputFile1(filename1,std::ios::binary);
    int i=1;
    try{
        GradeComment *gradeComm = new GradeComment;
        while(inputFile1 && !inputFile1.eof()){
            gradeComm->readFromFile(inputFile1);
            if(gradeComm->getGrade()!=0){
                studentDataDeleter.comments.push_back(gradeComm);
                ui->deleteComboBox->addItem(QString::number(i) + ". Ocena: " + QString::number(gradeComm->getGrade()) + " Komentarz: " + QString::fromStdString(gradeComm->getComment()));
                i++;
            }
            gradeComm = new GradeComment;
        }
        delete gradeComm;
    }
    catch (std::exception& e) {
        std::cerr << "Read data error: " << e.what() << std::endl;
    }
    inputFile1.close();

    string filename2= directoryName + ui->personComboBox->currentText().replace(' ','.').toStdString()+ "behaviorpORn.bin";
    std::ifstream inputFile2(filename2,std::ios::binary);
    try{
        PositiveOrNegativeComment *posOrNegComm = new PositiveOrNegativeComment;
        while(posOrNegComm->readFromFile(inputFile2) && !inputFile2.eof()){
            studentDataDeleter.comments.push_back(posOrNegComm);
            ui->deleteComboBox->addItem(QString::number(i) +". Typ: " + QString::fromStdString(posOrNegComm->getPositiveOrNegative()) + " Komentarz: " + QString::fromStdString(posOrNegComm->getComment()));
            i++;
            posOrNegComm = new PositiveOrNegativeComment;
        }
        delete posOrNegComm;
    }
    catch (std::exception& e) {
        std::cerr << "Read data error: " << e.what() << std::endl;
    }
    inputFile2.close();
}

Person currentTeacher;
TeacherRemoveData::TeacherRemoveData(QWidget *parent)
    : QDialog(parent)
    , ui(new Ui::TeacherRemoveData)
{
    studentDataDeleter.comments.clear();
    studentDataDeleter.grades.clear();

    ui->setupUi(this);
    ui->subjectComboBox->hide();
    ui->subjectLabel->hide();

    std::ifstream inputFil(directoryName + "usersdata.bin", std::ios::binary);
    if(!inputFil.is_open()){
        QMessageBox::warning(this,"file err","Couldn't open a file");
    }
    try {
        Person *currentUser = new Person;
        while(currentUser->read(inputFil) && !inputFil.eof()){
            if(currentUser->getRole()=="Uczeń"){
                ui->personComboBox->addItem(QString::fromStdString(currentUser->getName() + " " + currentUser->getSurname()));
            }
        }
        delete currentUser;
    }
    catch (std::exception& e) {
        std::cerr << "Read data error: " << e.what() << std::endl;
    }
    inputFil.close();

    for(auto i=0u;i<currentTeacher.subjects.size();i++){
        ui->subjectComboBox->addItem(QString::fromStdString(currentTeacher.subjects[i]));
    }
}

TeacherRemoveData::~TeacherRemoveData()
{
    delete ui;
}

void TeacherRemoveData::on_cancelPushButton_clicked()
{
    close();
}

void TeacherRemoveData::on_gradeRadioButton_clicked()
{
    ui->subjectComboBox->show();
    ui->subjectLabel->show();
    removeGradeUppdateData();
}

void TeacherRemoveData::on_behaviorRadioButton_clicked()
{
    ui->subjectComboBox->hide();
    ui->subjectLabel->hide();
    removeBehaviorUppdateData();
}

void TeacherRemoveData::on_personComboBox_currentIndexChanged()
{
    if(ui->gradeRadioButton->isChecked()){
        removeGradeUppdateData();
    }
    else if(ui->behaviorRadioButton->isChecked()){
        removeBehaviorUppdateData();
    }
}

void TeacherRemoveData::on_subjectComboBox_currentIndexChanged()
{
    if(ui->gradeRadioButton->isChecked()){
        removeGradeUppdateData();
    }
}
Teacher teacher1(currentTeacher);
void TeacherRemoveData::on_deletePushButton_clicked()
{
    if(ui->deleteComboBox->currentText()==""){
        QMessageBox::warning(this,"error","No data to remove");
        return;
    }
    if(ui->gradeRadioButton->isChecked()){
        int numberOfDeletedGrade = ui->deleteComboBox->currentText().left(ui->deleteComboBox->currentText().indexOf('.')).toInt() - 1;
        studentDataDeleter.grades.erase(studentDataDeleter.grades.begin() + numberOfDeletedGrade);
        QString gradesNameFile= QString::fromStdString(directoryName) + ui->personComboBox->currentText().replace(' ','.') + ui->subjectComboBox->currentText() + ".bin";
        std::ofstream outputFile(gradesNameFile.toStdString(), std::ios::binary | std::ios::trunc);
        try {
            for(auto i=0u;i<studentDataDeleter.grades.size();++i){
                teacher1.printGrades(outputFile,studentDataDeleter.grades.at(i));
            }
            outputFile.close();
        } catch (std::exception &e) {
            QMessageBox::warning(this, "Błąd", e.what());
            return ;
        }
        QMessageBox::information(this,"information","Deleted correctly");
        studentDataDeleter.grades.clear();       
        close();
    }
    else if(ui->behaviorRadioButton->isChecked()){
        int numberOfDeletedBehavior = ui->deleteComboBox->currentText().left(ui->deleteComboBox->currentText().indexOf('.')).toInt() - 1;
        studentDataDeleter.comments.erase(studentDataDeleter.comments.begin() + numberOfDeletedBehavior);
        QString filename1= QString::fromStdString(directoryName) + ui->personComboBox->currentText().replace(' ','.') +"behaviorGrade.bin";
        QString filename2= QString::fromStdString(directoryName) + ui->personComboBox->currentText().replace(' ','.') + "behaviorpORn.bin";
        QFile::remove(filename1);
        QFile::remove(filename2);
        std::ofstream outputFile1(filename1.toStdString(),std::ios::binary | std::ios::trunc);
        std::ofstream outputFile2(filename2.toStdString(),std::ios::binary | std::ios::trunc);
        GradeComment *gradeCommentPtr;
        PositiveOrNegativeComment *positiveOrNegativeCommentPtr;
        for(auto i=0u;i<studentDataDeleter.comments.size();i++){
            try{
                if(studentDataDeleter.comments[i]->getType()=="grade"){
                    gradeCommentPtr = dynamic_cast<GradeComment*>(studentDataDeleter.comments[i]);
                    if(gradeCommentPtr->getComment()!="brak"){
                        gradeCommentPtr->writeToFile(outputFile1);
                    }
                }
                else if(studentDataDeleter.comments[i]->getType()=="notGrade"){
                    positiveOrNegativeCommentPtr = dynamic_cast<PositiveOrNegativeComment*>(studentDataDeleter.comments[i]);
                    if(positiveOrNegativeCommentPtr->getComment()!="brak"){
                        positiveOrNegativeCommentPtr->writeToFile(outputFile2);
                    }
                }
            }
            catch (std::exception &e) {
                QMessageBox::warning(this, "Błąd", e.what());
                return ;
            }
        }
        outputFile2.close();
        outputFile1.close();
        QMessageBox::information(this,"information","Deleted correctly");
        studentDataDeleter.comments.clear();
        close();
    }
    else{
        QMessageBox::warning(this,"Error","Select data for remove");
    }        
}

