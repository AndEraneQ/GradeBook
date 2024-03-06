/********************************************************************************
** Form generated from reading UI file 'loginwindow.ui'
**
** Created by: Qt User Interface Compiler version 6.6.1
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_LOGINWINDOW_H
#define UI_LOGINWINDOW_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QDialog>
#include <QtWidgets/QGridLayout>
#include <QtWidgets/QHBoxLayout>
#include <QtWidgets/QLabel>
#include <QtWidgets/QLineEdit>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QVBoxLayout>

QT_BEGIN_NAMESPACE

class Ui_LoginWindow
{
public:
    QHBoxLayout *horizontalLayout;
    QHBoxLayout *horizontalLayout_4;
    QLabel *label_3;
    QVBoxLayout *verticalLayout_2;
    QVBoxLayout *verticalLayout;
    QHBoxLayout *horizontalLayout_2;
    QLabel *emailLabel;
    QLineEdit *emailLineEdit;
    QHBoxLayout *horizontalLayout_3;
    QLabel *passwordLabel;
    QLineEdit *passwordLineEdit;
    QGridLayout *gridLayout;
    QPushButton *cancelPushButton;
    QPushButton *loginPushButton;

    void setupUi(QDialog *LoginWindow)
    {
        if (LoginWindow->objectName().isEmpty())
            LoginWindow->setObjectName("LoginWindow");
        LoginWindow->resize(789, 353);
        LoginWindow->setStyleSheet(QString::fromUtf8("background-color: rgb(90, 90, 90);"));
        horizontalLayout = new QHBoxLayout(LoginWindow);
        horizontalLayout->setObjectName("horizontalLayout");
        horizontalLayout_4 = new QHBoxLayout();
        horizontalLayout_4->setObjectName("horizontalLayout_4");
        label_3 = new QLabel(LoginWindow);
        label_3->setObjectName("label_3");

        horizontalLayout_4->addWidget(label_3);

        verticalLayout_2 = new QVBoxLayout();
        verticalLayout_2->setObjectName("verticalLayout_2");
        verticalLayout = new QVBoxLayout();
        verticalLayout->setObjectName("verticalLayout");
        horizontalLayout_2 = new QHBoxLayout();
        horizontalLayout_2->setObjectName("horizontalLayout_2");
        emailLabel = new QLabel(LoginWindow);
        emailLabel->setObjectName("emailLabel");
        emailLabel->setStyleSheet(QString::fromUtf8("font: 700 15pt \"Nirmala UI\";\n"
"color: \"white\";"));

        horizontalLayout_2->addWidget(emailLabel);

        emailLineEdit = new QLineEdit(LoginWindow);
        emailLineEdit->setObjectName("emailLineEdit");
        emailLineEdit->setStyleSheet(QString::fromUtf8("font: 15pt \"Segoe UI\";\n"
"color: \"white\";"));

        horizontalLayout_2->addWidget(emailLineEdit);


        verticalLayout->addLayout(horizontalLayout_2);

        horizontalLayout_3 = new QHBoxLayout();
        horizontalLayout_3->setObjectName("horizontalLayout_3");
        passwordLabel = new QLabel(LoginWindow);
        passwordLabel->setObjectName("passwordLabel");
        passwordLabel->setStyleSheet(QString::fromUtf8("font: 700 15pt \"Nirmala UI\";\n"
"color: \"white\";"));

        horizontalLayout_3->addWidget(passwordLabel);

        passwordLineEdit = new QLineEdit(LoginWindow);
        passwordLineEdit->setObjectName("passwordLineEdit");
        passwordLineEdit->setStyleSheet(QString::fromUtf8("font: 15pt \"Segoe UI\";\n"
"color: \"white\";"));
        passwordLineEdit->setEchoMode(QLineEdit::Password);

        horizontalLayout_3->addWidget(passwordLineEdit);


        verticalLayout->addLayout(horizontalLayout_3);


        verticalLayout_2->addLayout(verticalLayout);

        gridLayout = new QGridLayout();
        gridLayout->setObjectName("gridLayout");
        cancelPushButton = new QPushButton(LoginWindow);
        cancelPushButton->setObjectName("cancelPushButton");
        cancelPushButton->setCursor(QCursor(Qt::OpenHandCursor));
        cancelPushButton->setStyleSheet(QString::fromUtf8("font: italic 22pt \"Trebuchet MS\";\n"
"color: \"white\";"));

        gridLayout->addWidget(cancelPushButton, 0, 0, 1, 1);

        loginPushButton = new QPushButton(LoginWindow);
        loginPushButton->setObjectName("loginPushButton");
        loginPushButton->setCursor(QCursor(Qt::OpenHandCursor));
        loginPushButton->setStyleSheet(QString::fromUtf8("font: italic 22pt \"Trebuchet MS\";\n"
"color: \"white\";"));

        gridLayout->addWidget(loginPushButton, 0, 1, 1, 1);


        verticalLayout_2->addLayout(gridLayout);


        horizontalLayout_4->addLayout(verticalLayout_2);


        horizontalLayout->addLayout(horizontalLayout_4);


        retranslateUi(LoginWindow);

        QMetaObject::connectSlotsByName(LoginWindow);
    } // setupUi

    void retranslateUi(QDialog *LoginWindow)
    {
        LoginWindow->setWindowTitle(QCoreApplication::translate("LoginWindow", "Dialog", nullptr));
        label_3->setText(QString());
        emailLabel->setText(QCoreApplication::translate("LoginWindow", "E-Mail", nullptr));
        passwordLabel->setText(QCoreApplication::translate("LoginWindow", "Has\305\202o", nullptr));
        cancelPushButton->setText(QCoreApplication::translate("LoginWindow", "Anuluj", nullptr));
        loginPushButton->setText(QCoreApplication::translate("LoginWindow", "Zaloguj", nullptr));
    } // retranslateUi

};

namespace Ui {
    class LoginWindow: public Ui_LoginWindow {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_LOGINWINDOW_H
