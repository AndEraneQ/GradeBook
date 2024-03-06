#include "studentwindow.h"
#include "ui_studentwindow.h"
#include <QMessageBox>
#include "person.h"
#include "student.h"
#include "mainwindow.h"
#include "fileData.h"
Person studentPerson;
StudentWindow::StudentWindow(QWidget *parent)
    : QDialog(parent)
    , ui(new Ui::StudentWindow)
{
    readCurrentUser(this,studentPerson,directoryName + "currentuser.bin");
    Student student(studentPerson);
    ui->setupUi(this);
    ui->nameLabel->setText(QString::fromStdString(student.getName()));
    ui->lastNameLabel->setText(QString::fromStdString(student.getSurname()));
}

StudentWindow::~StudentWindow()
{
    delete ui;
    delete showGrades;
    delete showBehavior;
}

void StudentWindow::on_exitPushButton_clicked()
{
    close();
    MainWindow *mainWindow;
    mainWindow = new MainWindow(this);
    mainWindow->show();
}

void StudentWindow::on_gradesPushButton_clicked()
{
    showGrades = new ShowGrades(this);
    showGrades->show();
}

void StudentWindow::on_behaviorPushButton_clicked()
{
    showBehavior = new ShowBehavior(this);
    showBehavior->show();
}

void StudentWindow::on_showDataPushButton_clicked()
{
    QMessageBox *msgBox;
    msgBox = new QMessageBox(this);
    msgBox->setStyleSheet("QMessageBox { background-color: rgb(90, 90, 90); }"
                          "QMessageBox QLabel { font: italic 12pt 'Trebuchet MS'; color: white; }");
    msgBox->setIcon(QMessageBox::Information);
    *msgBox << studentPerson;
    msgBox->show();
}

