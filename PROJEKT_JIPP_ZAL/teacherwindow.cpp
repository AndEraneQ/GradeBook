#include "teacherwindow.h"
#include "ui_teacherwindow.h"
#include "QMessageBox"
#include "teacher.h"
#include "person.h"
#include "mainwindow.h"
#include "fileData.h"
Person personTeacher;
TeacherWindow::TeacherWindow(QWidget *parent)
    : QDialog(parent)
    , ui(new Ui::TeacherWindow)
{
    readCurrentUser(this,personTeacher,directoryName + "currentuser.bin");
    Teacher teacher(personTeacher);
    ui->setupUi(this);
    ui->nameLabel->setText(QString::fromStdString(teacher.getName()));
    ui->lastNameLabel->setText(QString::fromStdString(teacher.getSurname()));
}

TeacherWindow::~TeacherWindow()
{
    delete ui;
    delete addBehavio;
    delete addGrades;
    delete teacherRemoveData;
    delete showDataTeacherWindow;
}

void TeacherWindow::on_logoutPushButton_clicked()
{
    this->close();
    MainWindow *mainWindow;
    mainWindow = new MainWindow(this);
    mainWindow->show();
}

void TeacherWindow::on_gradePushButton_clicked()
{
    addGrades = new AddGrades(this);
    addGrades->show();
}

void TeacherWindow::on_behaviorPushButton_clicked()
{
    addBehavio = new AddBehavio(this);
    addBehavio->show();
}

void TeacherWindow::on_showDataPushButton_clicked()
{
    QMessageBox *msgBox;
    msgBox = new QMessageBox(this);
    msgBox->setStyleSheet("QMessageBox { background-color: rgb(90, 90, 90); }"
                         "QMessageBox QLabel { font: italic 12pt 'Trebuchet MS'; color: white; }");
    msgBox->setIcon(QMessageBox::Information);
    *msgBox << personTeacher;
    msgBox->show();
}

void TeacherWindow::on_deleteDataPushButton_clicked()
{
    teacherRemoveData = new TeacherRemoveData(this);
    teacherRemoveData->show();
}

void TeacherWindow::on_showStudentDataPushButton_clicked()
{
    showDataTeacherWindow = new ShowDataTeacherWindow(this);
    showDataTeacherWindow->show();
}

