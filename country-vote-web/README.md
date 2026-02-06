# Country Vote Web

SPA frontend for Country Vote Challenge.

## Table of Contents
*   [Design Choices](#design-choices)
*   [Trade-offs](#trade-offs)
*   [Features](#features)
*   [Getting Started](#getting-started)

## Design Choices

Followed new official Angular Style Conventions (https://angular.dev/style-guide) to make it easier to maintain and scale applications.
Standalone components
State managements with signals
Project folder structure based on features

## Trade-offs

Some parts of the application could be improved, like error messages in user-form template may be confusing because of error messages boilerplate, and a directive can be implemented to manage error messages logic.

I decided not to use any component library for simplicity and ease to meet design prototypes in figma. This could slow down implementing new features as complex ui components would have to be created from scratch.

Few unit tests due to time constraints.


## Features

*   **Feature 1:** User form for country votes.
*   **Feature 2:** Table listing of 10 most voted countries with input search.

## Getting Started

### Prerequisites

Ensure you have the following installed on your machine:
*   [Node.js](https://nodejs.org) (v24+)
*   [npm](https://www.npmjs.com) (v11+) or [Yarn](https://yarnpkg.com)
*   [Angular CLI](https://angular.dev/tools/cli) (v21+)
*   [Angular] (https://angular.dev/) (21+)

### Running the Application

    `npm install`
    `npm run start`
Alternatively, if you are using local angular installation with npx, you can run the application with:
    
    `npm run start-npx`

The web app will be available at `http://localhost:4200/`.
