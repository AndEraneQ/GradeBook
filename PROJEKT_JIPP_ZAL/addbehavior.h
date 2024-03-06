#ifndef ADDBEHAVIOR_H
#define ADDBEHAVIOR_H

#include <QDialog>

namespace Ui {
class AddBehavior;
}

class AddBehavior : public QDialog
{
    Q_OBJECT

public:
    explicit AddBehavior(QWidget *parent = nullptr);
    ~AddBehavior();

private:
    Ui::AddBehavior *ui;
};

#endif // ADDBEHAVIOR_H
