# SatelliteApp

#  Summary

The application consists of **two pages.** The first page lists the **satellites** along with the **active passive informations.** The second page **shows detail informations** from the source selected on the first page.

## Techs used

 - Jetpack Compose
 - Compose Navigation
 - Dagger Hilt
 - Room
 - Coroutines - Flows
 
## Architecture used
 - Mvvm
 - Clean Architecture (with missing strategies, mentioned below)
 - Single Compose Activity

## What could be better ?
Of course, this repo could not be prepared in the best way due to the limited time. Here are some steps to make this repo more professional;
 - Usecases could be added to domain layer
 - Multimodule structure could be used
 - Some data mappers and result classes could be implemented to data layers (also error handling)
 - With multimodule structure, Compose Navigation will be implemented better, in accordance with the SOLID principles
 - Unit tests and Portrait mode could be added
 - Emitting the position informations could be implemented better(via a manager or separate repo)

## Note
The UI-UX never cared
