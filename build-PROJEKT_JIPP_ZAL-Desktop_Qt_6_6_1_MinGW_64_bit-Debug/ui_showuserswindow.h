/********************************************************************************
** Form generated from reading UI file 'showuserswindow.ui'
**
** Created by: Qt User Interface Compiler version 6.6.1
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_SHOWUSERSWINDOW_H
#define UI_SHOWUSERSWINDOW_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QDialog>
#include <QtWidgets/QLabel>
#include <QtWidgets/QPlainTextEdit>
#include <QtWidgets/QVBoxLayout>

QT_BEGIN_NAMESPACE

class Ui_ShowUsersWindow
{
public:
    QVBoxLayout *verticalLayout_2;
    QVBoxLayout *verticalLayout;
    QLabel *allUsersLabel;
    QPlainTextEdit *showUsersPlainTextEdit;

    void setupUi(QDialog *ShowUsersWindow)
    {
        if (ShowUsersWindow->objectName().isEmpty())
            ShowUsersWindow->setObjectName("ShowUsersWindow");
        ShowUsersWindow->resize(324, 285);
        ShowUsersWindow->setStyleSheet(QString::fromUtf8("background-color: rgb(90, 90, 90);"));
        verticalLayout_2 = new QVBoxLayout(ShowUsersWindow);
        verticalLayout_2->setObjectName("verticalLayout_2");
        verticalLayout = new QVBoxLayout();
        verticalLayout->setObjectName("verticalLayout");
        allUsersLabel = new QLabel(ShowUsersWindow);
        allUsersLabel->setObjectName("allUsersLabel");
        allUsersLabel->setStyleSheet(QString::fromUtf8("font: italic 14pt \"Trebuchet MS\";\n"
"color: \"white\";"));
        allUsersLabel->setTextFormat(Qt::AutoText);

        verticalLayout->addWidget(allUsersLabel);

        showUsersPlainTextEdit = new QPlainTextEdit(ShowUsersWindow);
        showUsersPlainTextEdit->setObjectName("showUsersPlainTextEdit");
        showUsersPlainTextEdit->setAutoFillBackground(true);
        showUsersPlainTextEdit->setStyleSheet(QString::fromUtf8("font: italic 11pt \"Trebuchet MS\";\n"
"color: \"white\";"));

        verticalLayout->addWidget(showUsersPlainTextEdit);


        verticalLayout_2->addLayout(verticalLayout);


        retranslateUi(ShowUsersWindow);

        QMetaObject::connectSlotsByName(ShowUsersWindow);
    } // setupUi

    void retranslateUi(QDialog *ShowUsersWindow)
    {
        ShowUsersWindow->setWindowTitle(QCoreApplication::translate("ShowUsersWindow", "Dialog", nullptr));
        allUsersLabel->setText(QCoreApplication::translate("ShowUsersWindow", "Wszyscy u\305\274ytkownicy: ", nullptr));
    } // retranslateUi

};

namespace Ui {
    class ShowUsersWindow: public Ui_ShowUsersWindow {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_SHOWUSERSWINDOW_H
