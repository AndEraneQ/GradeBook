#include "adminwindow.h"
#include "ui_adminwindow.h"
#include "mainwindow.h"

AdminWindow::AdminWindow(QWidget *parent)
    : QDialog(parent)
    , ui(new Ui::AdminWindow)
{
    ui->setupUi(this);
    QPixmap pix(":/1/a/adminWindowPicture");
    ui->photoLabel->setPixmap(pix.scaled(200,200,Qt::KeepAspectRatio));
}

AdminWindow::~AdminWindow()
{
    delete ui;
    delete registerWindow;
    delete removePersonWindow;
}

void AdminWindow::on_logoutPushButton_clicked()
{
    close();
    MainWindow *mainWindow;
    mainWindow = new MainWindow(this);
    mainWindow->show();
}

void AdminWindow::on_addPushButton_clicked()
{
    registerWindow = new RegisterWindow(this);
    registerWindow->show();
}

void AdminWindow::on_deletePushButton_clicked()
{
    removePersonWindow = new RemovePersonWindow(this);
    removePersonWindow->show();
}

