#ifndef TEACHERSHOWDATAWINDOW_H
#define TEACHERSHOWDATAWINDOW_H

#include <QDialog>

namespace Ui {
class TeacherShowDataWindow;
}

class TeacherShowDataWindow : public QDialog
{
    Q_OBJECT

public:
    explicit TeacherShowDataWindow(QWidget *parent = nullptr);
    ~TeacherShowDataWindow();

private:
    Ui::TeacherShowDataWindow *ui;
};

#endif // TEACHERSHOWDATAWINDOW_H
