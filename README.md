
# DeepaMehta 4 Webpages

This DeepaMehta 4 Plugin brings simple, _multi-site_ web-publishing capabilities to your DeepaMehta 4 installation. Installing it introduces the types _Webpage_, _Redirect_, _Menu Item_ and _Website_ to your DeepaMehta 4.

![Website Example: Setup of the Kiezatlas Webpages](https://github.com/mukil/dm4-webpages/raw/master/kiezatlas-website-setup-graph-only.png)

Feature-wise it allows for a global standard website and, if desired, this module provides each of your users their personal resource for publishing. Subsequently each website can appear in different style or layout, has its own menu items, footer and frontpage. Webpages can also extend the style or layout of their website and users are also allowd to load their own JavaScript for their page.

The HTML generated by this module tries to map (at best as possible) the DeepaMehta standard types to terms of the [Schema.org](https://schema.org) vocabulary.

It builds on our recent work, especially on the [dm4-thymeleaf](https://github.com/jri/dm4-thymeleaf) module.

## Installation Requirements

See [system requirements at jri/deepamehta](https://github.com/jri/deepamehta/#1-check-requirements)

It is recommended to use DeepaMehta 4.8.3 (or higher) and it is required to use the following

 * [dm4-thymeleaf-0.6.1](https://github.com/jri/dm4-thymeleaf) module

You'll find both available for download both at [http://download.deepamehta.de](http://download.deepamehta.de).

## Usage: Creating a Webpage

To start working a _Webpage_ simply use the `Create` menu in Toolbar (upper grey area of DeepaMehta).

To publish a _Webpage_ it must be connected to a _Website_.

1. Navigate and reveal your **Website** (e.g. via following the `My Website` button after clicking your `Username` in the `Toolbar`). The website topic allows you to edit and enter some basic information which occurs on all your webpages (Footer, About, Layout, etc):

You can also reveal your website through search `By Type` and then selecting `Website`.

2. Creating a _Webpage_ is as simple as using the `New Webpage` when having selected a `Website` item.

That's it. Your webpage is now published under your hostname and its so called _Web Alias_. The very same steps apply if you want to publish a _Redirect_ or a  _Menu item_ on your website.

Note: The permission who can see your published webpage depends on the so called _SharingMode_ of the workspace your webpage is assigned to.


## Changelog

**0.4.6** -- Nov 18, 2017

* Maintenance relase improving on the custom events fired for 3rd party plugins

**0.4.5** -- Nov 13, 2017

* Standard Theme: Integrate semantic-ui standard theme
* Multi-sites: Migrated username based prefixes to simple Website prefixes
* Top-navigation bar with support for dropdown menus
* Ajax based fulltext search in top nav bar
* New custom events for 3rd parties to hook into template preparation

**0.4.4** -- Jan 31, 2017

* Arrange site navigation blocks as `flexbox` layout
* Feature: Allow 3rd party plugins to hook in a top menu navigation fragment
* Feature: Allow 3rd party plugins to gather menu items and webpage topics (for the "standard" website)
* Fix: Website name is always "standard" on pages/templates registered by 3rd party plugins

**0.4.3** -- Jan 04, 2017

* Expose standard webpages and menu items to a 3rd party frontpage
* A CSS fix in the "standard grey" stylesheet fixing menu position on each page

**0.4.2** -- Nov 14, 2016

* Revision of "standard-grey" stylesheet
* Revision of server side java plugin code

**0.4.1** -- Nov 03, 2016

Pleae have a look at [this commit message](https://github.com/mukil/dm4-webpages/commit/79ad5ea048d440e780e58022bb51adcba62e18be) for a short description of fixes and improvements in 0.4.1.

**0.4** -- Aug 09, 2016

* Completely revised webpage application model
* Introduced new icons, types and a specific web-alias renderer
* Depends on dm4-thymeleaf module version 0.6.1
* Not compatible with previous version (dm4-webpages-0.3)
* Developer note: This version only installs migrations 1, 2 and 3.<br/>
  Migration 4 and 5 are still in flux and may instal with the next release.
* Uses Thymeleaf 2.1.3

Note: You cannot upgrade a _dm47-webpages-0.3_ installation to use _dm47-webpages-0.4_.

**0.3** -- Nov 23, 2015

* Useful to create and publish _one_ website
* Compatible with the collaborative DeepaMehta 4.7

**0.1.1** -- Oct 09, 2015

* Simple Web Pages for DeepaMehta 4.4.x

-----------
Malte Reißig<br/>
Copyright 2016
