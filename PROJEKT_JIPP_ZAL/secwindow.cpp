#include "secwindow.h"
#include "ui_secwindow.h"

secWindow::secWindow(QWidget *parent)
    : QDialog(parent)
    , ui(new Ui::secWindow)
{
    ui->setupUi(this);
}

secWindow::~secWindow()
{
    delete ui;
}
