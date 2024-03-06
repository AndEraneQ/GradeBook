#include "teachershowdatawindow.h"
#include "ui_teachershowdatawindow.h"

TeacherShowDataWindow::TeacherShowDataWindow(QWidget *parent) :
    QDialog(parent),
    ui(new Ui::TeacherShowDataWindow)
{
    ui->setupUi(this);
}

TeacherShowDataWindow::~TeacherShowDataWindow()
{
    delete ui;
}
