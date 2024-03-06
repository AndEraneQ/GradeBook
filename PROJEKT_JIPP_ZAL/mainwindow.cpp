#include "mainwindow.h"
#include "ui_mainwindow.h"
#include <qpixmap>
#include "qdir.h"

MainWindow::MainWindow(QWidget *parent)
    : QMainWindow(parent)
    , ui(new Ui::MainWindow)
{
    ui->setupUi(this);
    QPixmap pix(":/1/a/mainWindowPicture.png");
    int w=ui->photoLabel->width();
    int h=ui->photoLabel->height();
    ui->photoLabel->setPixmap(pix.scaled(w*6,h*6,Qt::KeepAspectRatio));
    QString currentPath = QDir::currentPath();
    QString newFolderName = "usersData";
    QString newFolderPath = currentPath + QDir::separator() + newFolderName;
    if (!QDir(newFolderPath).exists()) {
        QDir().mkdir(newFolderPath);
    }
    QString newFilePath = newFolderPath + QDir::separator() + "usersdata.bin";
    if (!QFile::exists(newFilePath)) {
        QFile newFile(newFilePath);
        newFile.open(QIODevice::WriteOnly);
        newFile.close();
    }
}

MainWindow::~MainWindow()
{
    delete ui;
    delete loginWindow;
    delete registerWindow;
}

void MainWindow::on_cancelPushButton_clicked()
{
    close();
}

void MainWindow::on_loginPushButton_clicked()
{
    hide();
    loginWindow = new LoginWindow(this);
    loginWindow->show();
}

void MainWindow::on_registerPushButton_clicked()
{
    hide();
    registerWindow = new RegisterWindow(this);
    registerWindow->show();
}

